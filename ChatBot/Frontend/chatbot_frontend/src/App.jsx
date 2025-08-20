import { useState, useEffect, useRef } from "react"
import "./App.css"

export default function ChatbotPage() {
    const [messages, setMessages] = useState([])
    const [inputValue, setInputValue] = useState("")
    const [secretarias, setSecretarias] = useState([])
    const [isLoading, setIsLoading] = useState(false)
    const [isTyping, setIsTyping] = useState(false)
    const [apiError, setApiError] = useState(null)
    const [showSecretarias, setShowSecretarias] = useState(true)
    const messagesEndRef = useRef(null)

    const API_BASE_URL = "http://localhost:8080"

    useEffect(() => {
        fetchSecretarias()
    }, [])

    useEffect(() => {
        scrollToBottom()
    }, [messages])

    useEffect(() => {
        if (secretarias.length > 0 && messages.length === 0) {
            const welcomeMessage = {
                id: 1,
                text: `Temos ${secretarias.length} secretarias disponíveis. Clique em uma para ver os serviços.`,
                isUser: false,
                timestamp: new Date(),
            }
            setMessages([welcomeMessage])
        }
    }, [secretarias, messages.length])

    const fetchSecretarias = async () => {
        try {
            setApiError(null)
            const response = await fetch(`${API_BASE_URL}/chatbot`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
                mode: "cors",
            })

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`)
            }

            const contentType = response.headers.get("content-type")
            if (!contentType || !contentType.includes("application/json")) {
                throw new Error("Resposta não é JSON válido")
            }

            const data = await response.json()
            setSecretarias(data)
            console.log("Secretarias carregadas:", data)
        } catch (error) {
            console.error("Erro ao buscar secretarias:", error)
            setApiError(`Erro ao conectar com o servidor: ${error instanceof Error ? error.message : "Erro desconhecido"}`)
        }
    }

    const scrollToBottom = () => {
        messagesEndRef.current?.scrollIntoView({ behavior: "smooth" })
    }

    const simulateTyping = () => {
        setIsTyping(true)
        setTimeout(() => {
            setIsTyping(false)
        }, 1000 + Math.random() * 2000)
    }

    // 🔹 Nova função: buscar serviços de uma secretaria
    const fetchServicosPorSecretaria = async (secretaria) => {
        setIsLoading(true)
        simulateTyping()
        try {
            const response = await fetch(`${API_BASE_URL}/chatbot/${secretaria.id}`)
            const data = await response.json()

            const userMessage = {
                id: Date.now(),
                text: `Quero informações sobre a ${secretaria.nome}`,
                isUser: true,
                timestamp: new Date(),
            }

            const botMessage = {
                id: Date.now() + 1,
                text:
                    data && data.length > 0
                        ? `A ${secretaria.nome} oferece os seguintes serviços: \n${data.map((s) => `- ${s.nome}`).join("\n")}`
                        : `Nenhum serviço encontrado para a ${secretaria.nome}.`,
                isUser: false,
                timestamp: new Date(),
            }

            setMessages((prev) => [...prev, userMessage, botMessage])
        } catch (error) {
            console.error("Erro ao buscar serviços:", error)
            setMessages((prev) => [
                ...prev,
                { id: Date.now(), text: "Erro ao buscar serviços. Tente novamente.", isUser: false, timestamp: new Date() },
            ])
        } finally {
            setIsLoading(false)
        }
    }

    const generateResponse = (userMessage) => {
        const lowerMessage = userMessage.toLowerCase()

        if (lowerMessage.includes("secretaria")) {
            if (secretarias.length > 0) {
                const secretariasList = secretarias.map((s) => `${s.nome}`).join("; ")
                return `Temos ${secretarias.length} secretarias disponíveis: ${secretariasList}.`
            }
            return "Estou buscando informações sobre as secretarias disponíveis..."
        }

        return "Obrigado pela sua mensagem! Você pode clicar em uma secretaria abaixo para ver seus serviços."
    }

    const handleSendMessage = async () => {
        if (!inputValue.trim()) return

        const userMessage = {
            id: Date.now(),
            text: inputValue,
            isUser: true,
            timestamp: new Date(),
        }

        setMessages((prev) => [...prev, userMessage])
        setInputValue("")
        setIsLoading(true)
        simulateTyping()

        setTimeout(() => {
            const botResponse = {
                id: Date.now() + 1,
                text: generateResponse(inputValue),
                isUser: false,
                timestamp: new Date(),
            }

            setMessages((prev) => [...prev, botResponse])
            setIsLoading(false)
        }, 1500 + Math.random() * 1000)
    }

    const handleKeyPress = (e) => {
        if (e.key === "Enter" && !e.shiftKey) {
            e.preventDefault()
            handleSendMessage()
        }
    }

    return (
        <div className="chatbot-container">
            <div className="chatbot-header">
                <div className="header-content">
                    <div className="bot-avatar">
                        <span>🤖</span>
                    </div>
                    <div className="header-info">
                        <h1>Assistente Virtual</h1>
                        <span className={`status ${apiError ? "offline" : "online"}`}>
                            {apiError ? "Modo Demo" : "Online"}
                        </span>
                    </div>
                    <button
                        className="toggle-secretarias"
                        onClick={() => setShowSecretarias(!showSecretarias)}
                        title={showSecretarias ? "Ocultar secretarias" : "Mostrar secretarias"}
                    >
                        📋
                    </button>
                </div>
            </div>

            {apiError && (
                <div className="api-error-banner">
                    <p>⚠️ Conectando ao servidor local (localhost:8080). Usando dados de demonstração.</p>
                    <button onClick={fetchSecretarias} className="retry-button">
                        Tentar Novamente
                    </button>
                </div>
            )}

            {showSecretarias && secretarias.length > 0 && (
                <div className="secretarias-panel">
                    <div className="secretarias-header">
                        <h3>📍 Secretarias Disponíveis ({secretarias.length})</h3>
                    </div>
                    <div className="secretarias-grid">
                        {secretarias.map((secretaria) => (
                            <div
                                key={secretaria.id}
                                className="secretaria-card cursor-pointer hover:bg-gray-100"
                                onClick={() => fetchServicosPorSecretaria(secretaria)} // 🔹 clique chama API
                            >
                                <h4>{secretaria.nome}</h4>
                                {secretaria.descricao && <p className="descricao">{secretaria.descricao}</p>}
                                <p className="endereco">📍 {secretaria.endereco}</p>
                            </div>
                        ))}
                    </div>
                </div>
            )}

            <div className="chatbot-messages">
                {messages.map((message) => (
                    <div key={message.id} className={`message ${message.isUser ? "user-message" : "bot-message"}`}>
                        <div className="message-content">
                            <p>{message.text}</p>
                            <span className="message-time">
                                {message.timestamp.toLocaleTimeString("pt-BR", {
                                    hour: "2-digit",
                                    minute: "2-digit",
                                })}
                            </span>
                        </div>
                    </div>
                ))}

                {isTyping && (
                    <div className="message bot-message">
                        <div className="message-content typing">
                            <div className="typing-indicator">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </div>
                    </div>
                )}

                <div ref={messagesEndRef} />
            </div>
        </div>
    )
}
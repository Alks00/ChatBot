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
                text: `Olá! Sou seu assistente virtual. Encontrei ${secretarias.length} secretarias disponíveis. Como posso ajudá-lo hoje?`,
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

            setSecretarias([
                {
                    id: 1,
                    nome: "Secretaria de Educação",
                    descricao: "Responsável pela educação municipal",
                    endereco: "Rua da Educação, 123 - Centro",
                },
                {
                    id: 2,
                    nome: "Secretaria de Saúde",
                    descricao: "Cuidados com a saúde pública",
                    endereco: "Av. da Saúde, 456 - Centro",
                },
                {
                    id: 3,
                    nome: "Secretaria de Obras",
                    descricao: "Infraestrutura e obras públicas",
                    endereco: "Rua das Obras, 789 - Industrial",
                },
            ])
        }
    }

    const scrollToBottom = () => {
        messagesEndRef.current?.scrollIntoView({ behavior: "smooth" })
    }

    const simulateTyping = () => {
        setIsTyping(true)
        setTimeout(
            () => {
                setIsTyping(false)
            },
            1000 + Math.random() * 2000,
        )
    }

    const generateResponse = (userMessage) => {
        const lowerMessage = userMessage.toLowerCase()

        if (lowerMessage.includes("secretaria") || lowerMessage.includes("departamento")) {
            if (secretarias.length > 0) {
                const secretariasList = secretarias.map((s) => `${s.nome} (${s.endereco})`).join("; ")
                return `Temos ${secretarias.length} secretarias disponíveis: ${secretariasList}. Sobre qual delas você gostaria de saber mais?`
            }
            return "Estou buscando informações sobre as secretarias disponíveis..."
        }

        if (
            lowerMessage.includes("endereço") ||
            lowerMessage.includes("endereco") ||
            lowerMessage.includes("localização")
        ) {
            if (secretarias.length > 0) {
                const enderecos = secretarias.map((s) => `${s.nome}: ${s.endereco}`).join("; ")
                return `Aqui estão os endereços das secretarias: ${enderecos}`
            }
            return "Não consegui carregar os endereços no momento."
        }

        const secretariaEncontrada = secretarias.find(
            (s) =>
                lowerMessage.includes(s.nome.toLowerCase()) ||
                s.nome.toLowerCase().includes(lowerMessage.replace("secretaria de ", "").replace("secretaria ", "")),
        )

        if (secretariaEncontrada) {
            return `${secretariaEncontrada.nome}: ${secretariaEncontrada.descricao || "Informações não disponíveis"}. Endereço: ${secretariaEncontrada.endereco}${secretariaEncontrada.servico && secretariaEncontrada.servico.length > 0 ? `. Serviços: ${secretariaEncontrada.servico.map((s) => s.nome).join(", ")}` : ""}`
        }

        if (lowerMessage.includes("horário") || lowerMessage.includes("funcionamento")) {
            return "O horário de funcionamento é de segunda a sexta-feira, das 8h às 17h."
        }

        if (lowerMessage.includes("contato") || lowerMessage.includes("telefone")) {
            return "Para mais informações, você pode entrar em contato pelo telefone (11) 1234-5678 ou pelo email contato@exemplo.com"
        }

        if (lowerMessage.includes("ajuda") || lowerMessage.includes("help")) {
            return "Posso ajudá-lo com informações sobre secretarias, endereços, horários de funcionamento, contatos e serviços disponíveis. O que você gostaria de saber?"
        }

        if (lowerMessage.includes("erro") || lowerMessage.includes("problema")) {
            return apiError
                ? `Detectei um problema de conexão: ${apiError}. Estou usando dados de exemplo para demonstração.`
                : "Não há problemas detectados no momento. Como posso ajudá-lo?"
        }

        return "Obrigado pela sua mensagem! Como posso ajudá-lo melhor? Você pode perguntar sobre secretarias específicas, endereços, horários ou contatos."
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

        setTimeout(
            () => {
                const botResponse = {
                    id: Date.now() + 1,
                    text: generateResponse(inputValue),
                    isUser: false,
                    timestamp: new Date(),
                }

                setMessages((prev) => [...prev, botResponse])
                setIsLoading(false)
            },
            1500 + Math.random() * 1000,
        )
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
                        <span className={`status ${apiError ? "offline" : "online"}`}>{apiError ? "Modo Demo" : "Online"}</span>
                    </div>
                    <button
                        className="toggle-secretarias"
                        onClick={() => setShowSecretarias(!showSecretarias)}
                        title={showSecretarias ? "Ocultar secretarias" : "Mostrar secretarias"}
                    >
                        {showSecretarias ? "📋" : "📋"}
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
                            <div key={secretaria.id} className="secretaria-card">
                                <h4>{secretaria.nome}</h4>
                                {secretaria.descricao && <p className="descricao">{secretaria.descricao}</p>}
                                <p className="endereco">📍 {secretaria.endereco}</p>
                                {secretaria.servico && secretaria.servico.length > 0 && (
                                    <div className="servicos">
                                        <span>Serviços: {secretaria.servico.map((s) => s.nome).join(", ")}</span>
                                    </div>
                                )}
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

            <div className="chatbot-input">
                <div className="input-container">
          <textarea
              value={inputValue}
              onChange={(e) => setInputValue(e.target.value)}
              onKeyPress={handleKeyPress}
              placeholder="Digite sua mensagem..."
              rows={1}
              disabled={isLoading}
          />
                    <button onClick={handleSendMessage} disabled={!inputValue.trim() || isLoading} className="send-button">
                        <span>📤</span>
                    </button>
                </div>
            </div>
        </div>
    )
}

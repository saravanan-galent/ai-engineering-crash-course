

# Applied AI Engineering: A Crash Course for Full Stack Developers

**Core Philosophy:** Experiential Learning ("Learn by Doing").

This curriculum follows a **"Theory-Tool-Action"** model. Each module moves from the foundational "why" to the industry-standard "how," ending with a hands-on experiment to solidify your skills.

---

## Module 01: Foundations (The Engine)

Before using LLMs, you must understand the neural architecture and learning paradigms that power them.

* **Theory: What is Machine Learning?**
Machine Learning (ML) is the shift from **explicit programming** (if-this-then-that) to **pattern recognition**. Instead of writing rules, we provide data and an algorithm that "learns" the rules by minimizing mathematical error.
* **The Three Types of Learning Systems**
1. **Supervised Learning:** Trained on labeled data (Input + Correct Answer). *Example: MNIST digit classification.*
2. **Unsupervised Learning:** Finds hidden patterns in unlabeled data. *Example: Clustering customers by behavior.*
3. **Reinforcement Learning (RL):** Learning through trial and error using rewards. *Note: RLHF is what makes LLMs conversational.*


* **Key Resources:** [Neural Networks: Zero to Hero](https://karpathy.ai/zero-to-hero.html) by Andrej Karpathy.
* **Experiment:** Understand how an image is processed as a matrix of weights.
* **Code Example:** [PyTorch MNIST Tutorial (GitHub)](https://github.com/pytorch/examples/tree/main/mnist)

---

## Module 02: Orchestration (Agents & Embeddings)

Moving from static code to autonomous reasoning using state-of-the-art frameworks.

* **Key Resources:** [LangChain Overview](https://python.langchain.com/docs/introduction/) & [LangGraph Overview](https://langchain-ai.github.io/langgraph/).
* **Theory:** What are Vector Embeddings?
* **Experiment:** Build an agent using **LangGraph**. Give it a **Calculator** tool and observe the "Plan-Execute-Observe" loop.
* **Code Example:** [LangGraph Quickstart Repo](https://github.com/langchain-ai/langgraph)

---

## Module 03: Retrieval (RAG, Hybrid Search & GraphRAG)

Giving AI long-term memory and the ability to reason over complex relationships.

### Concepts

* **Standard RAG:** Semantic similarity search using Vector Databases.
* **Hybrid Search:** Combining **Vector Search** (semantic meaning) with **Keyword Search** (exact terms/BM25). This is critical for finding specific product IDs or technical codes that vectors might "blur."
* **Semantic Caching:** Storing previous LLM answers to similar questions. If a new question is semantically close to a cached one, return the cached answer to save cost and latency.
* **GraphRAG:** Using Knowledge Graphs to answer questions about complex entity relationships.

### Key Resources

* **Hybrid Search:** [MongoDB Atlas Hybrid Search Guide](https://www.mongodb.com/docs/atlas/atlas-vector-search/hybrid-search/).
* **Caching:** [GPTCache Library](https://github.com/zilliztech/GPTCache) or [Redis Semantic Cache](https://www.google.com/search?q=https://redis.io/docs/latest/develop/interact/search-and-query/advanced-concepts/semantic-caching/).
* **Graph Theory:** [Microsoft GraphRAG Introduction](https://microsoft.github.io/graphrag/).

### Experiments

* **Hybrid PDF Search:** Build a service in MongoDB that finds documents using both semantic meaning and specific keywords (like "Invoice #123").
* **Semantic Cache Layer:** Implement a layer that checks a Vector DB for an existing answer before calling the LLM API.
* **Code Example:** [LangChain Hybrid Search Cookbook](https://www.google.com/search?q=https://github.com/langchain-ai/langchain/blob/master/cookbook/retrieval.ipynb)

---

## Module 04: Context Engineering (The Strategy)

Maintaining a high-IQ state while staying within token and cost budgets.

* **Key Resources:** [OpenAI Prompt Caching](https://platform.openai.com/docs/guides/prompt-caching) & [Anthropic Context Caching](https://docs.anthropic.com/en/docs/build-with-claude/prompt-caching).
* **Experiment:** Implement a manager that summarizes chat history every 5 messages and uses **Prompt Caching** for static system instructions.
* **Code Example:** [LangGraph Memory Implementation](https://langchain-ai.github.io/langgraph/how-tos/persistence/)

---

## Module 05: Interoperability (MCP & A2A)

The standards for connecting AI to your infrastructure.

* **Key Resources:** [Model Context Protocol (MCP) Official Docs](https://modelcontextprotocol.io/).
* **Experiments:** Create a server that exposes a local SQLite database to an LLM via the MCP protocol.
* **Code Example:** [Building your first MCP Server (Python/TS)](https://modelcontextprotocol.io/quickstart)

---

## Module 06: Production & Optimization

Hardening and fine-tuning for the real world.

* **Key Resources:** [NeMo Guardrails Documentation](https://github.com/NVIDIA/NeMo-Guardrails) & [Unsloth AI (GitHub)](https://github.com/unslothai/unsloth).
* **Experiment:** Fine-tune a small model (Llama 3 8B) on your own emails to learn a consistent professional tone.
* **Code Example:** [Fine-tuning Llama 3 with Unsloth (Colab)](https://www.google.com/search?q=https://colab.research.google.com/github/unslothai/unsloth/blob/main/notebooks/Llama_3_1_8B_Colab.ipynb)

---

## Useful Links & Technical Resources

| Category | Primary Theory & Documentation | Code Experiments & Repositories |
| --- | --- | --- |
| **Foundations** | Karpathy: Zero to Hero • 3Blue1Brown: Neural Networks • Attention Is All You Need Paper | PyTorch MNIST Tutorial • Karpathy Learning Log Repo |
| **Frameworks** | LangChain Docs • LangGraph Tutorials • Google ADK | LangGraph Quickstart • PocketFlow Minimalist Agent |
| **RAG & Search** | MongoDB Vector Search • Microsoft GraphRAG • HF Sentence Transformers | GenAI Showcase: RAG with MongoDB • LangChain GraphRAG Cookbook |
| **Context & Memory** | OpenAI Prompt Caching • Anthropic Context Caching • LangGraph Persistence | LangGraph Persistence Implementation • GPTCache: Semantic Caching |
| **Interoperability** | MCP Architecture • A2A Communication Standards | MCP Quickstart: Build a Server • MCP Servers Community List |
| **Production** | NVIDIA NeMo Guardrails • Unsloth AI • LangSmith: Eval & Monitoring | Unsloth Colab: Fine-tune Llama 3 • Pydantic-AI Structured Orchestration |
| **Roadmaps** | AI Engineer Cookbook 2026 • Full-Stack to AI Roadmap | Awesome-RAG List • Awesome-AI-Engineering |

---

Would you like me to expand on any specific module or provide a more detailed project outline for one of the experiments?
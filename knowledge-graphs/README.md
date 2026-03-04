# Knowledge Graphs — The Scaffold for Truth in the Age of LLM Hallucinations

An end-to-end interactive notebook that teaches Knowledge Graph fundamentals, builds real-world examples, and culminates in a working GraphRAG pipeline powered by LLMs.

## Quick Start

```bash
pip install networkx matplotlib pyvis rdflib plotly langchain-openai langchain-core pydantic
```

Open `index.ipynb` and run cells sequentially. An OpenAI API key is required for LLM-powered sections (Parts 4 and 7); all other sections work without one.

## Notebook Structure

| Part | Title | What You'll Learn |
|------|-------|-------------------|
| **1** | The Genesis — From "Strings to Things" | Semantic Web history, Google's Knowledge Graph, the "strings to things" revolution |
| **2** | The "Mind-Blowing" Mechanics | RDF triples, ontologies, logical inference, RDFS reasoning |
| **3** | State-of-the-Art | GraphRAG, Neuro-Symbolic AI, self-evolving graphs |
| **4** | GraphRAG in Action | LLM-powered entity extraction, automatic KG construction, natural language querying |
| **5** | Community Detection | Louvain algorithm, community summarization — the secret sauce behind Microsoft's GraphRAG |
| **6** | Graph Analytics | Degree centrality, betweenness centrality, PageRank — measuring entity importance |
| **7** | Code Intelligence | Parse a Java codebase into AST, build a code KG, query architecture with an LLM agent |
| **8** | The Technology Landscape | Tools, databases, research frontiers in the KG ecosystem |

## Hands-On Examples

### Example 1: Drug Discovery
A biomedical Knowledge Graph models diseases, drugs, proteins, and pathways. The notebook demonstrates how BenevolentAI discovered Baricitinib (an arthritis drug) as a COVID-19 treatment by reasoning over graph paths — a real discovery published in The Lancet.

### Example 2: Fraud Detection
A financial network of people, shell companies, offshore entities, and banks is modeled as a directed graph. A cycle detection algorithm finds three circular money laundering rings that no individual transaction analysis could catch.

### Example 3: Virtual Software Engineer
An AI agent's unified world model is represented as a KG spanning a codebase, documentation, tickets, and team knowledge — showing how KGs serve as an "operating system" for intelligent agents.

### Example 4: Code Intelligence (Part 7)
A sample Java e-commerce project is parsed at the AST level, loaded into a Knowledge Graph, visualized by architectural layer, and queried via an LLM agent using GraphRAG as a tool.

## Sample Java Project

The `sample-java-project/` directory contains a 21-file e-commerce application used in Part 7:

```
sample-java-project/src/main/java/com/ecommerce/
├── controller/          # REST API layer
│   ├── OrderController.java
│   └── ProductController.java
├── model/               # Domain entities
│   ├── BaseEntity.java
│   ├── Category.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── OrderStatus.java (enum)
│   ├── PaymentInfo.java
│   ├── PaymentMethod.java (enum)
│   ├── Product.java
│   ├── Review.java
│   ├── ShoppingCart.java
│   ├── User.java
│   └── UserRole.java (enum)
├── repository/          # Data access interfaces
│   ├── OrderRepository.java
│   └── ProductRepository.java
├── service/             # Business logic
│   ├── NotificationService.java
│   ├── OrderService.java
│   ├── PaymentService.java
│   └── ProductService.java
└── util/                # Utilities
    └── PriceCalculator.java
```

## Key Dependencies

| Package | Purpose |
|---------|---------|
| `networkx` | Graph construction, algorithms (cycle detection, centrality, PageRank, community detection) |
| `matplotlib` | 2D graph visualization |
| `plotly` | Interactive 3D graph visualization |
| `rdflib` | RDF/RDFS triple stores and semantic reasoning |
| `langchain-openai` | LLM integration for entity extraction and natural language querying |
| `pydantic` | Structured output schemas for LLM responses |

## Environment Variables

| Variable | Required For | Notes |
|----------|-------------|-------|
| `OPENAI_API_KEY` | Parts 4 and 7 (LLM sections) | Falls back to pre-extracted data if not set |

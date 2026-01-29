# SQLite MCP Server

This module demonstrates how to create and use a Model Context Protocol (MCP) server for SQLite database operations.

## Files

- `sqlite_mcp_server.py` - The MCP server implementation
- `mcp.ipynb` - Jupyter notebook with examples and demos
- `test_mcp_server.py` - Test script for the MCP server
- `sample_data.db` - Sample SQLite database (created by the notebook)

## Setup

1. Install dependencies:
```bash
pip install mcp langchain-openai langchain langchain-mcp-adapters
```

2. Create the sample database by running the cells in `mcp.ipynb`

## Running the MCP Server

### Method 1: Direct Python execution
```bash
python sqlite_mcp_server.py
```

### Method 2: Using uvx (recommended)
```bash
uvx mcp-server sqlite_mcp_server.py
```

### Method 3: Using the test script
```bash
python test_mcp_server.py
```

## Testing with MCP Inspector

You can test the server interactively using the MCP Inspector:

```bash
npx -y @modelcontextprotocol/inspector
```

Then connect to: `stdio://python sqlite_mcp_server.py`

## Available Tools

The MCP server exposes the following tools:

1. **execute_sql_query** - Execute SELECT queries on the database
   - Parameters: `query` (string), `db_path` (optional string)
   - Returns: JSON with query results

2. **get_table_schema** - Get the schema of a table
   - Parameters: `table_name` (string), `db_path` (optional string)
   - Returns: JSON with table schema information

3. **list_tables** - List all tables in the database
   - Parameters: `db_path` (optional string)
   - Returns: JSON with list of table names

4. **get_table_row_count** - Get the number of rows in a table
   - Parameters: `table_name` (string), `db_path` (optional string)
   - Returns: JSON with row count

## Available Resources

- **sqlite://database/{db_name}** - Get information about a database file

## Using with LangChain

The notebook (`mcp.ipynb`) shows how to:
1. Create sample data
2. Connect to the MCP server
3. Create an agent that uses MCP tools
4. Query the database using natural language

## Security Notes

- Only SELECT queries are allowed (no INSERT, UPDATE, DELETE)
- Database path validation prevents directory traversal
- All errors are caught and returned safely

## Example Queries

Once connected, you can ask questions like:
- "List all employees"
- "What is the total sales by department?"
- "Show me products with low stock"
- "Who is the top-performing salesperson?"

The agent will translate these to SQL queries and execute them safely through the MCP server.

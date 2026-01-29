#!/usr/bin/env python3
"""
Test script for the SQLite MCP Server

This script demonstrates how to use the MCP server programmatically.
"""

import asyncio
import json
from mcp import ClientSession, StdioServerParameters
from mcp.client.stdio import stdio_client

async def test_mcp_server():
    """Test the MCP server by calling its tools."""
    
    # Configure the server
    server_params = StdioServerParameters(
        command="python",
        args=["sqlite_mcp_server.py"],
        env=None
    )
    
    print("Connecting to MCP server...")
    
    async with stdio_client(server_params) as (read, write):
        async with ClientSession(read, write) as session:
            # Initialize the session
            await session.initialize()
            
            print("✓ Connected to MCP server")
            
            # List available tools
            tools = await session.list_tools()
            print(f"\nAvailable tools ({len(tools.tools)}):")
            for tool in tools.tools:
                print(f"  - {tool.name}: {tool.description}")
            
            # Test: List tables
            print("\n" + "="*60)
            print("Test 1: List tables")
            print("="*60)
            result = await session.call_tool("list_tables", {})
            print(json.dumps(json.loads(result.content[0].text), indent=2))
            
            # Test: Get table schema
            print("\n" + "="*60)
            print("Test 2: Get employees table schema")
            print("="*60)
            result = await session.call_tool("get_table_schema", {"table_name": "employees"})
            print(json.dumps(json.loads(result.content[0].text), indent=2))
            
            # Test: Execute SQL query
            print("\n" + "="*60)
            print("Test 3: Execute SQL query")
            print("="*60)
            result = await session.call_tool(
                "execute_sql_query",
                {"query": "SELECT name, department, salary FROM employees LIMIT 3"}
            )
            print(json.dumps(json.loads(result.content[0].text), indent=2))
            
            print("\n✓ All tests completed successfully!")

if __name__ == "__main__":
    print("Testing SQLite MCP Server")
    print("="*60)
    try:
        asyncio.run(test_mcp_server())
    except Exception as e:
        print(f"\n❌ Error: {e}")
        print("\nMake sure:")
        print("  1. The MCP package is installed: pip install mcp")
        print("  2. sqlite_mcp_server.py exists in the same directory")
        print("  3. sample_data.db exists (run the notebook first to create it)")

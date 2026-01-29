#!/usr/bin/env python3
"""
MCP Server for SQLite Database Operations

This server exposes SQLite database tools through the Model Context Protocol.
It allows AI agents to query SQLite databases safely using natural language.

Usage:
    python sqlite_mcp_server.py
    or
    uvx mcp-server sqlite_mcp_server.py
"""

import sqlite3
import json
import os
from pathlib import Path
from typing import Any, Optional
from mcp.server.fastmcp import FastMCP

# Initialize FastMCP server
mcp = FastMCP("SQLite MCP Server")

# Default database path (can be overridden)
DEFAULT_DB_PATH = "sample_data.db"


def get_db_path() -> str:
    """Get the database path, defaulting to sample_data.db in the same directory."""
    script_dir = Path(__file__).parent
    db_path = script_dir / DEFAULT_DB_PATH
    return str(db_path)


@mcp.tool()
def execute_sql_query(query: str, db_path: Optional[str] = None) -> str:
    """
    Execute a SQL SELECT query on the SQLite database and return results.
    Only SELECT queries are allowed for safety.
    
    Args:
        query: A SQL SELECT query to execute
        db_path: Optional path to the database file (defaults to sample_data.db)
        
    Returns:
        JSON string containing the query results
    """
    try:
        db = db_path if db_path else get_db_path()
        
        if not os.path.exists(db):
            return json.dumps({"error": f"Database file not found: {db}"})
        
        conn = sqlite3.connect(db)
        conn.row_factory = sqlite3.Row  # Return rows as dictionaries
        cursor = conn.cursor()
        
        # Only allow SELECT queries for safety
        query_upper = query.strip().upper()
        if not query_upper.startswith("SELECT"):
            conn.close()
            return json.dumps({"error": "Only SELECT queries are allowed for safety."})
        
        cursor.execute(query)
        columns = [description[0] for description in cursor.description]
        rows = cursor.fetchall()
        
        # Convert to list of dictionaries
        results = [dict(zip(columns, row)) for row in rows]
        
        conn.close()
        
        return json.dumps({
            "success": True,
            "row_count": len(results),
            "data": results
        }, indent=2)
    except sqlite3.Error as e:
        return json.dumps({"error": f"SQLite error: {str(e)}"})
    except Exception as e:
        return json.dumps({"error": f"Error executing query: {str(e)}"})


@mcp.tool()
def get_table_schema(table_name: str, db_path: Optional[str] = None) -> str:
    """
    Get the schema/structure of a table in the database.
    
    Args:
        table_name: Name of the table to inspect
        db_path: Optional path to the database file (defaults to sample_data.db)
        
    Returns:
        Schema information as a formatted string
    """
    try:
        db = db_path if db_path else get_db_path()
        
        if not os.path.exists(db):
            return json.dumps({"error": f"Database file not found: {db}"})
        
        conn = sqlite3.connect(db)
        cursor = conn.cursor()
        
        # Check if table exists
        cursor.execute("""
            SELECT name FROM sqlite_master 
            WHERE type='table' AND name=?
        """, (table_name,))
        
        if not cursor.fetchone():
            conn.close()
            return json.dumps({"error": f"Table '{table_name}' does not exist"})
        
        cursor.execute(f"PRAGMA table_info({table_name})")
        columns = cursor.fetchall()
        
        schema_info = {
            "table": table_name,
            "columns": []
        }
        
        for col in columns:
            col_info = {
                "name": col[1],
                "type": col[2],
                "not_null": bool(col[3]),
                "default_value": col[4],
                "primary_key": bool(col[5])
            }
            schema_info["columns"].append(col_info)
        
        conn.close()
        return json.dumps(schema_info, indent=2)
    except Exception as e:
        return json.dumps({"error": f"Error getting schema: {str(e)}"})


@mcp.tool()
def list_tables(db_path: Optional[str] = None) -> str:
    """
    List all tables in the database.
    
    Args:
        db_path: Optional path to the database file (defaults to sample_data.db)
        
    Returns:
        JSON string with list of table names
    """
    try:
        db = db_path if db_path else get_db_path()
        
        if not os.path.exists(db):
            return json.dumps({"error": f"Database file not found: {db}"})
        
        conn = sqlite3.connect(db)
        cursor = conn.cursor()
        
        cursor.execute("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name")
        tables = [row[0] for row in cursor.fetchall()]
        
        conn.close()
        return json.dumps({
            "success": True,
            "table_count": len(tables),
            "tables": tables
        }, indent=2)
    except Exception as e:
        return json.dumps({"error": f"Error listing tables: {str(e)}"})


@mcp.tool()
def get_table_row_count(table_name: str, db_path: Optional[str] = None) -> str:
    """
    Get the number of rows in a specific table.
    
    Args:
        table_name: Name of the table
        db_path: Optional path to the database file (defaults to sample_data.db)
        
    Returns:
        JSON string with row count
    """
    try:
        db = db_path if db_path else get_db_path()
        
        if not os.path.exists(db):
            return json.dumps({"error": f"Database file not found: {db}"})
        
        conn = sqlite3.connect(db)
        cursor = conn.cursor()
        
        cursor.execute(f"SELECT COUNT(*) FROM {table_name}")
        count = cursor.fetchone()[0]
        
        conn.close()
        return json.dumps({
            "success": True,
            "table": table_name,
            "row_count": count
        }, indent=2)
    except Exception as e:
        return json.dumps({"error": f"Error getting row count: {str(e)}"})


@mcp.resource("sqlite://database/{db_name}")
def get_database_info(db_name: str) -> str:
    """
    Get information about a database file.
    
    Args:
        db_name: Name of the database file
        
    Returns:
        Database information as JSON
    """
    try:
        script_dir = Path(__file__).parent
        db_path = script_dir / db_name
        
        if not os.path.exists(db_path):
            return json.dumps({"error": f"Database file not found: {db_path}"})
        
        conn = sqlite3.connect(str(db_path))
        cursor = conn.cursor()
        
        # Get all tables
        cursor.execute("SELECT name FROM sqlite_master WHERE type='table'")
        tables = [row[0] for row in cursor.fetchall()]
        
        # Get database size
        db_size = os.path.getsize(db_path)
        
        info = {
            "database": db_name,
            "path": str(db_path),
            "size_bytes": db_size,
            "table_count": len(tables),
            "tables": tables
        }
        
        conn.close()
        return json.dumps(info, indent=2)
    except Exception as e:
        return json.dumps({"error": f"Error getting database info: {str(e)}"})


if __name__ == "__main__":
    # Run the MCP server
    # This will use stdio transport by default
    mcp.run()

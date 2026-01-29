# Setup Instructions

This guide will help you set up the AI Engineering Crash Course project on both Windows and macOS.

## Prerequisites

- Python 3.8 or higher installed on your system
- pip (Python package installer) - usually comes with Python

## 1. Set up a Python Virtual Environment

A virtual environment isolates your project dependencies from other Python projects on your system.

### macOS / Linux

1. **Open Terminal** and navigate to the project directory:
   ```bash
   cd /path/to/ai-engineering-crash-course
   ```

2. **Create a virtual environment**:
   ```bash
   python3 -m venv venv
   ```
   or
   ```bash
   python -m venv venv
   ```

3. **Activate the virtual environment**:
   ```bash
   source venv/bin/activate
   ```
   
   When activated, you'll see `(venv)` at the beginning of your terminal prompt.

4. **Deactivate** (when you're done working):
   ```bash
   deactivate
   ```

### Windows

1. **Open Command Prompt or PowerShell** and navigate to the project directory:
   ```cmd
   cd C:\path\to\ai-engineering-crash-course
   ```

2. **Create a virtual environment**:
   ```cmd
   python -m venv venv
   ```
   or
   ```cmd
   py -m venv venv
   ```

3. **Activate the virtual environment**:
   
   **In Command Prompt (cmd)**:
   ```cmd
   venv\Scripts\activate.bat
   ```
   
   **In PowerShell**:
   ```powershell
   venv\Scripts\Activate.ps1
   ```
   
   If you get an execution policy error in PowerShell, run:
   ```powershell
   Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
   ```
   Then try activating again.
   
   When activated, you'll see `(venv)` at the beginning of your command prompt.

4. **Deactivate** (when you're done working):
   ```cmd
   deactivate
   ```

## 2. Install Dependencies

With your virtual environment activated, install the required packages:

```bash
pip install -r requirements.txt
```

This will install all the necessary packages including:
- langchain
- langchain-openai
- langchain-community
- pypdf
- faiss-cpu
- tiktoken
- mcp
- langchain-mcp-adapters
- nest-asyncio
- and more...

## 3. Set up Environment Variables (.env file)

The project requires API keys for OpenAI and Anthropic. Create a `.env` file in the project root directory.

### macOS / Linux

1. **Create the `.env` file**:
   ```bash
   touch .env
   ```

2. **Open it in a text editor**:
   ```bash
   nano .env
   ```
   or use any text editor like VS Code, Sublime Text, etc.

3. **Add the following content**:
   ```env
   OPENAI_API_KEY=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
   ANTHROPIC_API_KEY=sk-ant-api03-_hKbN0u4I1sDG÷ikm_BbcHkMn33pmgAQVe6JgGb7RsShxpY9ro203sJPeWNTYUNkVPwSlUdhvqGoZCqnQrTvWjg-kRmp9gAA
   ```

4. **Save and close** the file (in nano: press `Ctrl+X`, then `Y`, then `Enter`)

### Windows

1. **Create the `.env` file**:
   
   **Option A - Using Command Prompt**:
   ```cmd
   type nul > .env
   ```
   
   **Option B - Using PowerShell**:
   ```powershell
   New-Item -Path .env -ItemType File
   ```
   
   **Option C - Using Notepad**:
   ```cmd
   notepad .env
   ```
   When Notepad opens, click "Yes" to create the file.

2. **Add the following content** to the `.env` file:
   ```env
   OPENAI_API_KEY=sk-proj-********
   ANTHROPIC_API_KEY=sk-ant-api03-_*********
   ```

3. **Save the file** (Ctrl+S in Notepad or your text editor)

### Important Notes about .env file:

- **Never commit `.env` to version control** - it contains sensitive API keys
- The `.env` file should be in the project root directory (same level as `requirements.txt`)
- Make sure there are **no spaces** around the `=` sign
- Each variable should be on its own line
- Do not add quotes around the values unless your API keys contain special characters that require them

## 4. Verify Installation

Test that everything is set up correctly:

```bash
python -c "import langchain; print('LangChain installed successfully!')"
python -c "import openai; print('OpenAI SDK installed successfully!')"
```

## 5. Running Jupyter Notebooks

If you plan to use Jupyter notebooks:

```bash
pip install jupyter ipykernel
python -m ipykernel install --user --name=ai-engineering-crash-course
```

Then start Jupyter:
```bash
jupyter notebook
```

## Troubleshooting

### Virtual Environment Issues

- **"python: command not found"** (macOS/Linux): Try `python3` instead of `python`
- **"python is not recognized"** (Windows): Make sure Python is installed and added to PATH
- **Activation not working**: Make sure you're in the correct directory and the `venv` folder exists

### .env File Issues

- **File not found**: Make sure the `.env` file is in the project root directory
- **API keys not loading**: Check that `python-dotenv` is installed (`pip install python-dotenv`)
- **Syntax errors**: Ensure there are no spaces around `=` and each variable is on a new line

### Import Errors

- **Module not found**: Make sure your virtual environment is activated and you've run `pip install -r requirements.txt`
- **Version conflicts**: Try upgrading pip: `pip install --upgrade pip`

## Next Steps

Once setup is complete, you can start working through the modules:

1. **Module 01**: Learning basics
2. **Module 02**: Agents with LangChain
3. **Module 03**: RAG (Retrieval Augmented Generation)
4. **Module 04**: Memory Management
5. **Module 05**: Model Context Protocol (MCP)

Happy coding! 🚀
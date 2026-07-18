from flask import Flask, render_template
import os

app = Flask(__name__)

# ── Page Routes ───────────────────────────────────────────────────────────────
# Auth is now fully handled by the Spring Boot gateway (/api/auth/login, /api/auth/signup).
# Flask only serves HTML — the JWT lives in the browser's localStorage and is attached
# by JavaScript to every API request sent to the gateway.

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/login')
def login_page():
    return render_template('login.html')


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=os.environ.get('FLASK_DEBUG', 'false').lower() == 'true')


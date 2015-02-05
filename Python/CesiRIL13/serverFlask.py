__author__ = 'Dos Santos Julien'
from flask import Flask
app = Flask(__name__)

@app.route("/")
def charlie():
    return "Je suis CHARLIE !"

if __name__ == "__main__":
    app.run()
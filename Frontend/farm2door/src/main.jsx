import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'

// to setup eslint for vite
// 1) npm install eslint vite-plugin-eslint eslint-config-react-app
// 2) add 'react-app' to the extends section in .eslintrc.cjs
// 3) add eslint() to the plugins section in vite.config.js (import eslint from 'vite-plugin-eslint')

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)

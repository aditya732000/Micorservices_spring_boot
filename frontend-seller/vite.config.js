import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(),
    tailwindcss()
  ],
  server : {
    allowedHosts : ["5173-aditya7812-ecommercemic-h7ba9h2xbki.ws-us117.gitpod.io"]
  }
})

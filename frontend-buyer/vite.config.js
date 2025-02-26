import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server : {
    allowedHosts : ["5174-aditya7812-ecommercemic-h7ba9h2xbki.ws-us118.gitpod.io"],
    port:5174
  }

})

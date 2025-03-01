import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'
// https://vite.dev/config/
export default defineConfig({
  plugins: [react(),
    tailwindcss()
  ],
  server : {
    allowedHosts : ["5174-aditya7812-ecommercemic-h7ba9h2xbki.ws-us118.gitpod.io", "5174-aditya7812-ecommercemic-5oxq92yh6rp.ws-us118.gitpod.io"],
    port:5174
  }

})

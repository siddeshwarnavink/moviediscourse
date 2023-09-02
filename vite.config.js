import { defineConfig } from 'vite';
import laravel from 'laravel-vite-plugin';
import react from '@vitejs/plugin-react';

export default defineConfig({
    plugins: [
        laravel({
            input: ['resources/js/components/commentSection/index.jsx'],
            refresh: true,
        }),
        react(),
    ],
});

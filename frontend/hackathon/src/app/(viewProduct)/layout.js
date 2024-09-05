// /app/viewProduct/layout.js
import React from 'react';
import { Inter } from 'next/font/google';
import ViewProduct from './view/page';

const inter = Inter({ subsets: ['latin'] });

export default function RootLayout({ children }) {
  return (
    <main style={{ backgroundColor: '#f0f0f0', minHeight: '100vh',  }}>
      <ViewProduct/>
    </main>
  );
}


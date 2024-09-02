import { Inter } from "next/font/google";
import "./globals.css";

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "Hamburgueria burguer service",
  description: "Hamburgueres deliciosos só encontrado aqui",
};

export default function RootLayout({ children }) {
  return (
    <html className="bg-primary" lang="pt-br">
      <body className={inter.className}>{children}</body>
    </html>
  );
}

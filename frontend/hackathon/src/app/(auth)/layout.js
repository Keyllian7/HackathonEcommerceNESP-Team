import { Inter } from "next/font/google";
import Image from "next/image";

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "Hamburgueria burguer service",
  description: "Hamburgueres deliciosos só aqui",
};

export default function RootLayout({ children }) {
  return (
    <main className="md:flex md:items-center md:justify-center h-screen">
      <div className="flex flex-col justify-center items-center h-full md:h-fit md:bg-white md:flex-row md:max-w-[900px] sm:min-w-[300px] sm:rounded-lg">
        <div className="flex bg-secondary justify-center items-center py-6 w-full md:hidden">
          <h1 className="text-3xl text-white font-bold text-center">Hamburgueria burguer service</h1>
        </div>
        <div className="hidden md:flex sm:min-w-[350px]">
          <Image
            className="object-cover md:rounded-tl-lg md:rounded-bl-lg"
            src="/images/image-login-desktop.png"
            alt="Burger Service Logo"
            width={1500}
            height={1500}
          />
        </div>
        <div className="flex flex-col justify-center items-center h-full">
          {children}
        </div>
        {/* <div className="flex items-end absolute bottom-0 sm:hidden">
          <Image
            className="object-cover w-screen max-w-[700px]"
            src="/images/imagens-footer-cadastro.png"
            alt="Burger Service Logo"
            width={700}
            height={250}
          />
        </div> */}
      </div>
    </main>
  );
}

import { CardProduct } from "./components/CardProduct";
import { PriceSummary } from "./components/PriceSummary";

export const generateMetadata = async () => {
    return {
        title: "Hamburgueria burguer service",
        description: "Hamburgueres deliciosos já encontrado aqui",
    };
}

export default function Cart() {
    return (
        <main className="flex flex-col gap-4 px-4 pb-2">
            <a href="/">Voltar (temporario)</a>
            <h1 className="text-xl font-bold">Carrinho</h1>

            {Array(3).fill(0).map((_, i) => <CardProduct key={i} />)}
            
            <PriceSummary />

            <button className="bg-red-500 rounded-xl w-full h-10 text-white font-bold">Finalizar pedido</button>
        </main>
    )
}
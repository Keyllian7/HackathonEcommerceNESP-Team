"use client"

import { useState } from "react";
import { CardProduct } from "./components/CardProduct";
import { PriceSummary } from "./components/PriceSummary";

// TODO: Implementar dinamicamente
const products = [
    {
        id: 1,
        title: "batata",
        price: 1.99,
        description: "um limao",
        extra: "cebola, alface e tomate",
        image: "/images/moda-da-casa.png"
    },
    {
        id: 2,
        title: "hamburguer",
        price: 49.99,
        description: "Um hambuerguer delicioso",
        extra: "carne, queijo, tomate e mostarda",
        image: "/images/Hamburguer.png"
    },
    {
        id: 3,
        title: "hamburguer",
        price: 200.99,
        description: "Um hambuerguer delicioso",
        extra: "carne, queijo, tomate e mostarda e ketchup",
        image: "/images/Hamburguer.png"
    }
]

const priceProducts = products.reduce((total, product) => total + product.price, 0)

export default function Cart() {
    const [newTotal, setNewTotal] = useState(priceProducts)

    const handleUpdateTotal = (updateTotal) => {
        setNewTotal((prevTotal) => prevTotal + updateTotal)
    }


    // TODO: Implementar  versão desktop
    return (
        <main className="flex flex-col gap-4 px-4 pb-2">
            <a href="/">Voltar (temporario)</a>
            <h1 className="text-xl font-bold">Carrinho</h1>

            {products.map(product => (
                <CardProduct key={product.id} onUpdateTotal={handleUpdateTotal} products={product} />
            ))}

            <PriceSummary price={newTotal} freight={5} />

            <button className="bg-red-500 rounded-xl w-full h-10 text-white font-bold">Finalizar pedido</button>
        </main>
    )
}
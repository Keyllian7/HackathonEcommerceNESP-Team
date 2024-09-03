"use client"

import Image from "next/image"
import { useState } from "react"

export const CardProduct = () => {
    const [quantity, setQuantity] = useState(1)

    const incrementQuantity = () => {
        setQuantity(quantity + 1)
    }

    const decrementQuantity = () => {
        if (quantity > 1) {
            setQuantity(quantity - 1)
        }
    }

    return (
        <div className="bg-white flex items-center rounded-xl pr-2 py-2">
            <Image src="/images/moda-da-casa.png" alt="Burger Service Logo" width={100} height={100} />
            <div className="flex flex-col gap-4 w-full">
                <div>
                    <h2 className="text-lg font-bold">1x Moda da Casa</h2>
                    <span>R$ 34,90</span>
                </div>
                <div className="flex flex-col">
                    <h3>Adicionais:</h3>
                    <span>1x Hamburguer</span>
                    <span>1x Hamburguer</span>
                    <span>1x Hamburguer</span>
                </div>
            </div>
            <div className="flex justify-between items-center bg-red-500 rounded-xl w-20 px-2">
                <span className="cursor-pointer" onClick={decrementQuantity}>-</span>
                <span>{quantity}</span>
                <span className="cursor-pointer" onClick={incrementQuantity}>+</span>
            </div>
        </div>
    )
}
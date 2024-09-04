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
        <div className="bg-white flex flex-col items-center rounded-xl pr-2 py-3">
            <Image src="/images/moda-da-casa.png" alt="Burger Service Logo" width={200} height={200} />
            <div className="flex flex-col justify-center gap-4 w-full px-4">
                <div className="flex gap-4 items-center justify-between">
                    <div>
                        <h2 className="text-lg font-bold">Moda da Casa</h2>
                        <span>R$ 34,90</span>
                    </div>
                    <div className="flex justify-between items-center bg-orange-400 rounded-xl w-32 px-2">
                        <span className="cursor-pointer text-xl" onClick={decrementQuantity}>-</span>
                        <span>{quantity}</span>
                        <span className="cursor-pointer text-xl" onClick={incrementQuantity}>+</span>
                    </div>
                </div>
                <div className="flex flex-col gap-1">
                    <h3 className="text-sm">Adicionais:</h3>
                    <div className="flex flex-wrap gap-x-4 gap-y-2 text-xs text-gray-700">
                        <span>1x Hamburguer</span>
                        <span>1x Hamburguer</span>
                        <span>1x Hamburguer</span>
                        <span>1x Hamburguer</span>
                        <span>1x Hamburguer</span>
                    </div>
                </div>
                <button className="bg-slate-600 hover:bg-slate-600/70 text-white transition-colors py-2 px-4 w-fit rounded-lg">
                    Adicionar observação
                </button>
            </div>
        </div>
    )
}
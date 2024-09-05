"use client"

import Image from "next/image"
import { useEffect, useState } from "react"
import { ObservationModal } from "./ObservationModal"

export const CardProduct = ({ products, onUpdateTotal }) => {
    const [open, setOpen] = useState(false)
    const [quantity, setQuantity] = useState(1)
    const [totalPrice, setTotalPrice] = useState(products.price)
    const [prevTotal, setPrevTotal] = useState(products.price)
    const [observation, setObservation] = useState("Nenhuma observação")

    useEffect(() => {
        const newPrice = products.price * quantity

        if (onUpdateTotal) {
            onUpdateTotal(newPrice - prevTotal)
        }

        setTotalPrice(newPrice)
        setPrevTotal(newPrice)
    }, [quantity, products.price, onUpdateTotal, prevTotal])

    const incrementQuantity = () => setQuantity(quantity + 1)

    const decrementQuantity = () => {
        if (quantity > 1) {
            setQuantity(quantity - 1)
        }
    }

    const handleOpenModal = () => setOpen(true)
    const handleCloseModal = () => setOpen(false)

    return (
        <div className="bg-white flex flex-col items-center rounded-xl pr-2 py-3 drop-shadow-xl">
            <Image src={products.image} alt="Burger Service Logo" width={200} height={200} />
            <div className="flex flex-col justify-center gap-4 w-full px-4">
                <div className="flex gap-4 items-center justify-between">
                    <div>
                        <h2 className="text-lg font-bold">{products.title}</h2>
                        <span>R$ {totalPrice.toFixed(2)}</span>
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
                        <span>{products.extra}</span>
                    </div>
                </div>
                <div>
                    <h3 className="text-sm">Observação:</h3>
                    <span className="text-xs text-gray-600">{observation}</span>
                </div>
                <button onClick={handleOpenModal} className="bg-slate-600 hover:bg-slate-600/70 text-white transition-colors py-2 px-4 w-fit rounded-lg">
                    Adicionar observação
                </button>

                <ObservationModal
                    handleOpenModal={open}
                    handleCloseModal={handleCloseModal}
                    setObservation={setObservation}
                />
            </div>
        </div>
    )
}
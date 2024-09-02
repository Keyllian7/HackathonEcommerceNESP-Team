import Image from "next/image"

export const BestSellers = () => {
    return (
        <section>
            <h2 className="text-lg font-semibold mb-6">Mais vendidos</h2>
            <div className="grid grid-cols-2 gap-4">
                <div className="bg-white p-4 rounded-lg shadow-lg">
                    <Image src="/images/moda-da-casa.png" alt="Moda da Casa" className="w-full h-24 object-contain mb-2 rounded-lg" width={200} height={200} />
                    <h3 className="font-semibold">Moda da Casa</h3>
                    <div className="flex items-center">
                        <div className="w-full flex justify-end items-end ">
                            <p className="items-start w-full">R$ 34,90</p>
                            <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Favoritar" width={200} height={200} />
                        </div>
                    </div>
                </div>
                <div className="bg-white p-4 rounded-lg shadow-lg">
                    <Image src="/images/Hamburguer.png" alt="X-Tudo" className="w-full h-24 object-contain mb-2 rounded-lg" width={200} height={200} />
                    <h3 className="font-semibold">X-Tudo</h3>
                    <div className="flex items-center">
                        <div className="w-full flex justify-end items-end ">
                            <p className="items-start w-full">R$ 17,50</p>
                            <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Favoritar" width={200} height={200} />
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}
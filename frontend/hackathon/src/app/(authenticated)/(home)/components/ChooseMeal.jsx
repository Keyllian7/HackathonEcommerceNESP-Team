import Image from "next/image"

export const ChooseMeal = () => {
    return (
        <section className="mb-6">
            <h2 className="text-lg font-semibold">Escolha a sua refeição!</h2>
            <div className="flex space-x-4 mt-2">
                <button className="bg-secondary text-white py-2 px-4 rounded-full flex items-center">
                    BURGER  <Image src="/images/Hambuguer-Emoji.png" alt="Mudar depois" width={25} height={25} />
                </button>
                <button className="bg-tertiary text-black py-2 px-4 rounded-full flex items-center">
                    BEBIDAS  <Image src="/images/Bebidas-Emoji.png" alt="Mudar depois" width={25} height={25} />
                </button>
            </div>
        </section>
    )
}
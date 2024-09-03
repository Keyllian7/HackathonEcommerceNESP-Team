import Image from "next/image"

export const ChooseMeal = ({setCategoriaSelecionada, categoriaSelecionada}) => {
    return (
        <section className="mb-6">
            <h2 className="text-lg font-semibold">Escolha a sua refeição!</h2>
            <div className="flex space-x-4 mt-6">
                <button 
                    className={`py-2 px-4 rounded-full flex items-center ${
                        categoriaSelecionada === "burguer"
                        ? "bg-secondary text-white"
                        : "bg-gray-300 text-gray-700"
                    }`}
                    onClick={() => setCategoriaSelecionada("burguer")}>
                    BURGER  <Image src="/images/Hambuguer-Emoji.png" alt="Burguer" width={25} height={25} />
                </button>
                <button 
                    className={`py-2 px-4 rounded-full flex items-center ${
                        categoriaSelecionada === "bebidas"
                        ? "bg-secondary text-white"
                        : "bg-gray-300 text-gray-700"
                    }`}
                    onClick={() => setCategoriaSelecionada("bebidas")}>
                    BEBIDAS  <Image src="/images/Bebidas-Emoji.png" alt="Bebidas" width={25} height={25} />
                </button>
            </div>
        </section>
    )
}
import Image from "next/image"

export const BestSellers = ({categoriaSelecionada}) => {
    const Items = {
        burguer: [
            { id: 1, nome: 'Moda da Casa', preço: 'R$ 34,90', imagem: '/images/Moda da casa.png' },
            { id: 2, nome: 'X-Tudo', preço: 'R$ 17,50', imagem: '/images/X-Tudo.png' },
        ],
        bebidas: [
            { id: 1, nome: 'Coca-Cola', preço: 'R$ 5,00', imagem: '/images/cocaCola.png' },
            { id: 2, nome: 'Guaraná', preço: 'R$ 8,00', imagem: '/images/Guarana.png' },
        ]
    };

    const selecionarItems = Items[categoriaSelecionada];

    return (
        <section>
            <div className="flex justify-between font-semibold mb-2 p-2">
                <h2>Mais Vendidos</h2>
                <a href="/products" className="text-sm font-light text-black hover:underline">Ver Todos</a>
            </div>
            <div className="grid grid-cols-2 gap-4">
                {selecionarItems.map((Item) => (
                    <div key={Item.nome} className="bg-white p-4 rounded-lg shadow-lg">
                        <Image src={Item.imagem} alt={Item.nome} className="w-full h-24 object-contain mb-2 rounded-lg" width={200} height={200} />
                        <h3 className="font-semibold">{Item.nome}</h3>
                        <div className="flex items-center">
                            <div className="w-full flex justify-end items-end ">
                                <p className="items-start w-full">{Item.preço}</p>
                                <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Favoritar" width={200} height={200} />
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </section>
    );
}
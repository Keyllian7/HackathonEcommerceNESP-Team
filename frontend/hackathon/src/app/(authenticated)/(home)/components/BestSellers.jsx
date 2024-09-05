import Image from "next/image"
import { useState, useEffect } from "react";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Items } from "../../(Items)/components/Items";
import { adicionarAosFavoritos } from "../../utils/adicionarFavorites";

export const BestSellers = ({categoriaSelecionada}) => {

    const [favoritos, setFavoritos] = useState([]);
    const selecionarItems = Items[categoriaSelecionada];

    useEffect(() => {
        const favoritosSalvos = JSON.parse(localStorage.getItem('favoritos')) || [];
        setFavoritos(favoritosSalvos);
    }, []);

    const melhoresItens = selecionarItems.slice(0, 2)

    return (
        <section>
            <ToastContainer/>
            <div className="flex justify-between font-semibold mb-2 p-2">
                <h2>Mais Vendidos</h2>
                <a href="/products" className="text-sm font-light text-black hover:underline">Ver Todos</a>
            </div>
            <div className="grid grid-cols-2 gap-4">
                {melhoresItens.map((Item) => (
                    <div key={Item.nome} className="bg-white p-4 rounded-lg shadow-lg">
                        <Image src={Item.imagem} alt={Item.nome} className="w-full h-24 object-contain mb-2 rounded-lg" width={200} height={200} />
                        <h3 className="font-semibold">{Item.nome}</h3>
                        <div className="flex items-center">
                            <div className="w-full flex justify-end items-end ">
                                <p className="items-start w-full">{Item.preço}</p>
                                <Image className="flex object-contain w-8 cursor-pointer mt-2" src="/images/heart.png" alt="Favoritar" width={200} height={200} onClick={() => adicionarAosFavoritos(Item, favoritos, setFavoritos)} />
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </section>
    );
}
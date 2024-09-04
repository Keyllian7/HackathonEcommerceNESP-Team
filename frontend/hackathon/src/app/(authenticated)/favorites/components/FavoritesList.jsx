"use client"

import { useState, useEffect } from "react";
import Image from "next/image";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export const FavoritesList = () => {
    const [favoritos, setFavoritos] = useState([]);

    useEffect(() => {
        const favoritosSalvos = JSON.parse(localStorage.getItem('favoritos')) || [];
        setFavoritos(favoritosSalvos);
    }, []);

    const removerDosFavoritos = (id) => {
        const novosFavoritos = favoritos.filter((Item) => Item.id !== id);
        setFavoritos(novosFavoritos);
        localStorage.setItem('favoritos', JSON.stringify(novosFavoritos));
        toast.error('Item removido dos favoritos!');
    };

    return (
        <div className="w-full grid  items-center gap-x-4 gap-y-4 bg-quaternary p-6 text-white">
            <ToastContainer/>
            {favoritos.length === 0 ? (
                <p className="flex justify-center font-mono">Você ainda não tem favoritos!</p>
            ) : (
                favoritos.map((Item) => (
                    <div key={Item.id} className="bg-white p-4 rounded-lg flex flex-col items-center text-black">
                        <Image src={Item.imagem} alt={Item.nome} className="w-full h-24 object-contain mb-2 rounded-lg" width={200} height={200} />
                        <h3 className="font-semibold">{Item.nome}</h3>
                        <p>{Item.preço}</p>
                        <Image
                            src="/images/Trash.png"
                            alt="Remover dos Favoritos"
                            width={24}
                            height={24}
                            className="cursor-pointer mt-2"
                            onClick={() => removerDosFavoritos(Item.id)}
                        />
                    </div>
                ))
            )}
        </div>
    );
};

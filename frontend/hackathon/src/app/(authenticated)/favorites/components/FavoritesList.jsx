"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { removerDosFavoritos } from "../../utils/removerFavorites";
import Link from "next/link";

export const FavoritesList = () => {
  const [favoritos, setFavoritos] = useState([]);

  useEffect(() => {
    const favoritosSalvos = JSON.parse(localStorage.getItem("favoritos")) || [];
    setFavoritos(favoritosSalvos);
  }, []);

  return (
    <div className="w-full grid items-center gap-x-4 gap-y-4 bg-quaternary p-6 text-white">
      <ToastContainer />
      {favoritos.length === 0 ? (
        <p className="flex justify-center font-mono">
          Você ainda não tem favoritos!
        </p>
      ) : (
        favoritos.map((Item) => (
          <div
            key={Item.id}
            className="bg-white p-4 rounded-lg flex flex-col items-center text-black"
          >
            <Image
              src={Item.imagem}
              alt={Item.nome}
              className="w-full h-24 object-contain mb-2 rounded-lg"
              width={200}
              height={200}
            />
            <h3 className="font-semibold">{Item.nome}</h3>
            <p className="mb-2">{Item.preço}</p>
            <div className="flex items-center justify-between w-full">
              <Link href={`/viewProdutics?id=${Item.id}`}>
                <button className="bg-primary text-white px-4 py-2 rounded-md border border-transparent hover:bg-secondary transition-colors duration-300 focus:outline-none focus:ring-2 focus:ring-primary focus:ring-opacity-50">
                  Ver Detalhes
                </button>
              </Link>
              <Image
                src="/images/Trash.png"
                alt="Remover dos Favoritos"
                width={35}
                height={35}
                className="cursor-pointer ml-2"
                onClick={() =>
                  removerDosFavoritos(Item.id, favoritos, setFavoritos)
                }
              />
            </div>
          </div>
        ))
      )}
    </div>
  );
};

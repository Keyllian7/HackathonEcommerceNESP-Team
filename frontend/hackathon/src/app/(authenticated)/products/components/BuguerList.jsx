"use client"

import Image from "next/image";
import { adicionarAosFavoritos } from "../../utils/adicionarFavorites";
import { Items } from "../../(Items)/components/Items";
import { useState, useEffect } from 'react';
import { ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export const BurguerList = () => {

  const [favoritos, setFavoritos] = useState([]);
  const categoriaSelecionada = 'burguer';
  const selecionarItems = Items[categoriaSelecionada] || [];

  useEffect(() => {
      const favoritosSalvos = JSON.parse(localStorage.getItem('favoritos')) || [];
      setFavoritos(favoritosSalvos);
  }, []);

  return (
    <section>
      <ToastContainer/>
      <div className="grid grid-cols-2 gap-4 bg-quaternary p-6">
      {Array.isArray(selecionarItems) && selecionarItems.length > 0 ? (
        selecionarItems.map((Item) => (
          <div key={Item.id} className="bg-white p-4 rounded-lg flex flex-col items-center">
            <Image
              src={Item.imagem}
              alt={Item.nome}
              width={150}
              height={150}
              className="rounded-lg"/>
            <h2 className="mt-5 text-lg font-semibold">{Item.nome}</h2>
            <div className="w-full flex justify-end items-end">
              <p className="items-start w-full">{Item.preço}</p>
              <Image 
                className="flex object-contain w-8 cursor-pointer mt-2" 
                src="/images/heart.png" 
                alt="Favoritar" 
                width={200} height={200} 
                onClick={() => adicionarAosFavoritos(Item, favoritos, setFavoritos)}/>
              </div>
            </div>
        ))
      ):(
        <p>Nenhum Burguer Cadastrado</p>
      )}
      </div>
    </section>
  );
};
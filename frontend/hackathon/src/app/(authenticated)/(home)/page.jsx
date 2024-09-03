"use client";

import { NavBar } from "./components/NavBar";
import { ChooseMeal } from "./components/ChooseMeal";
import { BestSellers } from "./components/BestSellers";
import { Promotion } from "./components/Promotion";
import { OptionsBar } from "./components/OptionsBar";
import { useState } from "react";

export default function LandingPage() {

  const [categoriaSelecionada, setCategoriaSelecionada] = useState("burguer");

  return (
    <div className="flex flex-col min-h-screen">
      <main className="flex-grow"> 
        <NavBar />
        <ChooseMeal 
          setCategoriaSelecionada={setCategoriaSelecionada} 
          categoriaSelecionada={categoriaSelecionada} />
        <BestSellers categoriaSelecionada={categoriaSelecionada} />
        <Promotion />
      </main>
        <OptionsBar/>
    </div>
  );
}

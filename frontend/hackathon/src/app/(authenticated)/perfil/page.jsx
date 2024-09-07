import React from "react";
import { BackBar } from "../bebidaslist/components/BackBar";
import { OptionsBarHome } from "../bebidaslist/components/OptionsBarHome";
import { PerfilUsuario } from "./components/PerfilUsuario";

export default function VisualizarPerfil() {
  return (
    <div className="flex flex-col min-h-screen">
      <main className="flex-grow">
        <BackBar />
        <PerfilUsuario />
      </main>
      <OptionsBarHome />
    </div>
  );
}

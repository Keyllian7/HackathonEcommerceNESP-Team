"use client";

import React from "react";
import { EditarPerfil } from "../components/EditarPerfil";
import { BackBar } from "../../bebidaslist/components/BackBar";

export default function EditarPerfilPage() {
  return (
    <div className="flex flex-col min-h-screen">
      <main className="flex-grow">
        <BackBar />
        <EditarPerfil />
      </main>
    </div>
  );
}

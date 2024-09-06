import Image from "next/image";
import Link from "next/link";
import { usuario } from "../usuario";

export const PerfilUsuario = () => {

  return (
    <section className="mt-8 flex flex-col items-center max-w-md mx-auto p-8 bg-white rounded-xl shadow-lg border border-tertiary">
      <h1 className="text-3xl font-semibold mb-4 text-primary">Seu Perfil</h1>

      <div className="mb-6">
        <Image
          src="/images/account-circle.png"
          width={130}
          height={130}
          alt="Foto de Perfil"
          className="rounded-full border-4 border-tertiary shadow-sm transition-transform duration-300 hover:scale-105"
        />
      </div>

      <div className="text-2xl font-medium text-secondary mb-1">
        {usuario.nome}
      </div>

      <div className="w-full space-y-3 text-left text-base text-quaternary leading-relaxed mt-2">
        <div>
          <span className="font-semibold">Email: </span>
          {usuario.email}
        </div>
        <div>
          <span className="font-semibold">Endereço: </span>
          {usuario.endereco}
        </div>
        <div>
          <span className="font-semibold">Cep: </span>
          {usuario.cep}
        </div>
        <div>
          <span className="font-semibold">Complemento: </span>
          {usuario.complemento}
        </div>
        <div>
          <span className="font-semibold">Referência: </span>
          {usuario.referencia}
        </div>
      </div>

      <Link href="/perfil/editar">
        <button
          type="button"
          className="mt-6 w-full py-3 px-6 bg-primary text-white font-medium rounded-full shadow-md transition-transform duration-300 transform hover:scale-105 hover:bg-secondary focus:outline-none focus:ring-2 focus:ring-secondary focus:ring-opacity-50"
        >
          Editar Perfil
        </button>
      </Link>
    </section>
  );
};

import Image from "next/image";
export const PerfilUsuario = () => {
  return (
    <section className="mt-6  flex items-center flex-col ">
      <div className=" text-3xl m-5">
        <h1>
          {" "}
          <strong>Seu Perfil</strong>
        </h1>
      </div>
      <div className="">
        {" "}
        <Image src="/images/account-circle.png" width={300} height={200} />{" "}
      </div>
      <div className="text-3xl m-4">
        <h2>
          <strong>Usuário </strong>
        </h2>{" "}
      </div>
      <div className=" mb-2 mr-10">
        <label for="input-email">
          {" "}
          <strong>Email: </strong>
        </label>
        <input id="input-email" name="nome" type="text" />
      </div>
      <div className=" mb-2 mr-10 ">
        <label for="input-senha">
          <strong>Senha: </strong>
        </label>
        <input id="input-senha" name="senha" type="text"></input>
      </div>
      <div className=" mb-2 mr-4">
        <label for="input-Endereço">
          <strong>Endereço: </strong>
        </label>
        <input id="input-Endereço" name="Endereço" type="text"></input>
      </div>
      <div className="mb-2 mr-14">
        <label for="input-cep">
          <strong>Cep: </strong>{" "}
        </label>
        <input id="input-cep" name="cep" type="text"></input>
      </div>
      <div className=" mb-2 ml-4">
        <label for="input-Complemento">
          <strong>Complemento:</strong>{" "}
        </label>
        <input id="input-Complemento" name="Complemento" type="text" />
      </div>
      <div className="mb-2 mr-4">
        <label for="input-Referencia">
          <strong>Referência: </strong>{" "}
        </label>
        <input id="input-Referencia" name="Referencia" type="text" />
      </div>
      <div className=" w-[150px] h-[35px] bg-black mt-[100px] text-white pt-1 rounded-[9px] pl-7">
        <button type="submit">
          <strong>Editar Perfil</strong>{" "}
        </button>
      </div>
    </section>
  );
};

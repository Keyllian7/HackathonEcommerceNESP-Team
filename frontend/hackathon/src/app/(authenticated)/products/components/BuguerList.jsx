import Image from "next/image";

export const BurguerList = () => {
  return (
    <div className="grid grid-cols-2 gap-4 bg-quaternary p-6">
      <div className="bg-white p-4 rounded-lg flex flex-col items-center">
        <Image
          src="/images/Moda da casa.png"
          alt="Moda da Casa"
          width={150}
          height={150}
          className="rounded-lg"
        />
        
        <h2 className="mt-5 text-lg font-semibold">Moda da Casa</h2>
        <div className="w-full flex justify-end items-end">
          <p className="items-start w-full">R$ 34,90</p>
          <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Favoritar" width={200} height={200} />
        </div>
      </div>

      <div className="bg-white p-4 rounded-lg flex flex-col items-center">
        <Image
          src="/images/Hamburguer.png"
          alt="X-Tudo"
          width={150}
          height={150}
          className="rounded-lg"
        />
        <h2 className="mt-9 text-lg font-semibold">X-Tudo</h2>
        <div className="w-full flex justify-end items-end">
          <p className="items-start w-full">R$ 17,50</p>
          <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Favoritar" width={200} height={200} />
        </div>
      </div>
      
      <div className="bg-white p-4 rounded-lg flex flex-col items-center">
        <Image
          src="/images/Interrogação.png"
          alt="Indefinido"
          width={150}
          height={150}
          className="rounded-lg"
        />
        <h2 className="mt-9 text-lg font-semibold">Adicionar Burguer</h2>
        <div className="w-full flex justify-end items-end">
          <p className="items-start w-full">R$ 0</p>
          <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Adicionar" width={200} height={200} />
        </div>
      </div>

      <div className="bg-white p-4 rounded-lg flex flex-col items-center">
        <Image
          src="/images/Interrogação.png"
          alt="Indefinido"
          width={150}
          height={150}
          className="rounded-lg"
        />
        <h2 className="mt-9 text-lg font-semibold">Adicionar Burguer</h2>
        <div className="w-full flex justify-end items-end">
          <p className="items-start w-full">R$ 0</p>
          <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Adicionar" width={200} height={200} />
        </div>
      </div>

      <div className="bg-white p-4 rounded-lg flex flex-col items-center">
        <Image
          src="/images/Interrogação.png"
          alt="Indefinido"
          width={150}
          height={150}
          className="rounded-lg"
        />
        <h2 className="mt-9 text-lg font-semibold">Adicionar Burguer</h2>
        <div className="w-full flex justify-end items-end">
          <p className="items-start w-full">R$ 0</p>
          <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Adicionar" width={200} height={200} />
        </div>
      </div>

      <div className="bg-white p-4 rounded-lg flex flex-col items-center">
        <Image
          src="/images/Interrogação.png"
          alt="Indefinido"
          width={150}
          height={150}
          className="rounded-lg"
        />
        <h2 className="mt-9 text-lg font-semibold">Adicionar Burguer</h2>
        <div className="w-full flex justify-end items-end">
          <p className="items-start w-full">R$ 0</p>
          <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Adicionar" width={200} height={200} />
        </div>
      </div>

      <div className="bg-white p-4 rounded-lg flex flex-col items-center">
        <Image
          src="/images/Interrogação.png"
          alt="Indefinido"
          width={150}
          height={150}
          className="rounded-lg"
        />
        <h2 className="mt-9 text-lg font-semibold">Adicionar Burguer</h2>
        <div className="w-full flex justify-end items-end">
          <p className="items-start w-full">R$ 0</p>
          <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Adicionar" width={200} height={200} />
        </div>
      </div>

      <div className="bg-white p-4 rounded-lg flex flex-col items-center">
        <Image
          src="/images/Interrogação.png"
          alt="Indefinido"
          width={150}
          height={150}
          className="rounded-lg"
        />
        <h2 className="mt-9 text-lg font-semibold">Adicionar Burguer</h2>
        <div className="w-full flex justify-end items-end">
          <p className="items-start w-full">R$ 0</p>
          <Image className="flex object-contain w-8 " src="/images/heart.png" alt="Adicionar" width={200} height={200} />
        </div>
      </div>

    </div>
  );
};

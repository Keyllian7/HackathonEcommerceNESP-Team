import Image from "next/image";

export const Promotion = () => {
  return (
    <section className="mt-6">
      <div className="bg-quaternary text-white flex items-center justify-between">
        <div className="flex flex-col p-4 items-center">
          <p className="font-bold text-2xl">25% de desconto</p>
          <p className="text-lg">na sua primeira compra!</p>
        </div>
        <div className="h-full flex items-center">
          <Image
            src="/images/mulher-comendo-hamburguer.png"
            alt="Imagem promocional"
            width={350}
            height={100}
            className="h-full object-cover"
          />
        </div>
      </div>
    </section>
  );
};

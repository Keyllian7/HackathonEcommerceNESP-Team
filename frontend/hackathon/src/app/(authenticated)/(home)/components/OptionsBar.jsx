import Image from "next/image";
import Link from "next/link";

export const OptionsBar = () => {
  return (
    <section className="mt-">
      <div className="bg-fifth text-white flex items-center justify-between">
        <div className="h-full flex space-x-4 items-center pt-2 justify-center w-full">
          <Link href="/favorites">
            <Image
              src="/images/CoraçãoCinza.png"
              alt="Imagem promocional"
              width={50}
              height={100}
              className="h-full object-cover"
            />
          </Link>
          <Link href="/cart">
            <Image
              src="/images/Sacola.png"
              alt="Cart"
              width={50}
              height={100}
              className="h-full object-cover"
            />
          </Link>
        </div>
      </div>
    </section>
  );
};

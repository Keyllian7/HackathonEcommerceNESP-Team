import { BackBar } from "../burguerlist/components/BackBar";
import { OptionsBarHome } from "../burguerlist/components/OptionsBarHome";
import { FavoritesList } from "./components/FavoritesList";

export const generateMetadata = async () => {
  return {
    title: "Hamburgueria burguer service",
    description: "Só os melhores hamburgueres encontrados aqui",
  };
};

export default function ListProductsFavorites() {
  return (
    <div className="flex flex-col min-h-screen">
      <main className="flex-grow">
        <BackBar />
        <h1 className="text-xl font-bold flex justify-center p-2">
          Seus Favoritos
        </h1>
        <FavoritesList />
      </main>
      <OptionsBarHome />
    </div>
  );
}

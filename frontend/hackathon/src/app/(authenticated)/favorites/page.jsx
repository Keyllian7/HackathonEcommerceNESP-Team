import { BackBar } from "../products/components/BackBar";
import { OptionsBarHome } from "../products/components/OptionsBarHome";
import { FavoritesList } from "./components/FavoritesList";

export const generateMetadata = async () => {
    return {
      title: "Hamburgueria burguer service",
      description: "Só os melhores hamburgueres encontrados aqui",
    };
}

  export default function ListProductsFavorites() {
    return (
      <div className="flex flex-col min-h-screen">
        <main className="flex-grow"> 
          <BackBar />
          <FavoritesList/>
        </main>
          <OptionsBarHome/>
      </div>
    );
  }
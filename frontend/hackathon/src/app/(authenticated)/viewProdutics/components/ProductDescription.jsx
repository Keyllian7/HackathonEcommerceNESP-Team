import Image from "next/image";
export const ProductDescription = () => {
    return(
        <section className="mt-6  flex items-center flex-col " >
        <div className=" mt-[50px] w-[400px] bg-white rounded-3xl "> 
           <div className=" mt-6 text-center text-[35px]"> <strong>Moda da Casa</strong></div>
           <p className="text-center text-[20px] mb-6">Carne bovina grelhada, queijo cheddar,
               bacon crocante, alface, tomate e mollho,
                especial no pão artesanal.</p>
             <div className="flex items-center flex-col"><Image src="/images/Moda da casa.png"  width={350} height={100} /> </div>
             <div className="flex justify-center">
             <div className="mr-[60px] text-[25px]"><strong>Valor R$ 34,90</strong></div>
             <div className="pb-6"><Image src="/images/add_circle.png" width={35} height={50}  /></div>
             <div className="text-[25px]"><p><strong>0</strong></p></div>
             <div className="pb-6"> <Image src="/images/Minus circle.png "  width={34} height={50}/></div>
             </div>
            
        </div>
    </section>
    );
}


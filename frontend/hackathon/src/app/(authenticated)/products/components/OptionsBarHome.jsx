import Image from "next/image"

export const OptionsBarHome = () => {
    return (
        <section className="mt-6">
            <div className="bg-fifth text-white flex items-center justify-between">
                <div className="h-full flex space-x-4 items-center pt-2 justify-center w-full">
                    <Image src="/images/CoraçãoCinza.png" alt="Favortios" width={50} height={100} className="h-full object-cover"/>
                    <Image src="/images/HomeButton.png" alt="Home Button" width={50} height={100} className="h-full object-cover"/>
                    <Image src="/images/Sacola.png" alt="Cart" width={50} height={100} className="h-full object-cover"/>

                </div>
            </div>
            
        </section>
    )
}
import Section from "../components/section";
import NavigationButton from "../components/navigationButton";
import { FaAngleLeft } from "react-icons/fa";
import Image from "next/image";

const Custom404 = () => {
  return (
    <Section delay={0.2}>
      <div className="container mx-auto pt-6 text-center">
        <Image
          src="/cat/sad.svg" // Path to your SVG
          alt="Sad Cat"
          className="mx-auto mb-4 max-w-md pl-10"
          width={400}
          height={400}
        />
        <h1 className="text-4xl max-w-md mx-auto bg-gradient-to-r from-red-950 via-orange-500 to-red-400 text-transparent bg-clip-text animate-gradient">
          404 - Oops!
        </h1>
        <h2 className="text-xl max-w-md mx-auto mb-28 bg-gradient-to-r from-red-200 via-orange-500 to-white text-transparent bg-clip-text animate-gradient">
          Page not found
        </h2>
        <NavigationButton path="/" buttonText="Go home" Icon={FaAngleLeft} />
      </div>
    </Section>
  );
};

export default Custom404;

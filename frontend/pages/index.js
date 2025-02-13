import Hero from "../components/hero";
import Layout from "../layouts/article";
import LiquidButton from "../components/liquidButton";
import Section from "../components/section";

const options = {
  text: "Get started",
  width: 200,
  height: 50,
  color1: "#f3c5ff", // back layer color
  color2: "#f9f871", //button color
  color3: "#FFFFFF", //hover color
  textColor: "#000000",
};

const Home = () => (
  <Layout title={"Home"}>
    <div className="relative container mx-auto items-center">
      <video
        autoPlay
        loop
        muted
        playsInline
        preload="auto"
        className="absolute opacity-40 lg:opacity-100 w-[700px] h-full right-1 top-[10%] z-[-1]"
      >
        <source src="/hero/sphere.mp4" type="video/mp4" />
      </video>

      {/* Hero Section */}
      <div className="container items-center max-w-full flex flex-col md:flex-row justify-evenly pt-0 md:pt-20">
        <div className="w-full md:w-md p-6 pt-0">
          <Hero />
        </div>
      </div>

      <Section delay={0.6}>
        <div className="flex justify-center md:justify-start">
          <LiquidButton options={options} />
        </div>
      </Section>
    </div>
  </Layout>
);

export default Home;

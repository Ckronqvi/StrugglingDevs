import Section from "../components/section";

const Hero = () => {
  return (
    <div className="text-center md:text-left min-h-">
      <Section delay={0.2}>
        <h1
          className="text-6xl font-extrabold font-code leading-tight md:leading-snug
                   text-white"
        >
          Empowering New Developers.
          <br />
          Supporting Small
          <br /> Businesses.
        </h1>
      </Section>
      <Section delay={0.4}>
        <h2 className="mt-6 text-3xl font-medium font-code max-w-xl bg-gradient-to-r from-[#f7b3d6] via-[#00FFFF] to-[#90769d] text-transparent bg-clip-text animate-gradient">
          Connect with small businesses in need of tech solutions while building
          your portfolio!
        </h2>
      </Section>
    </div>
  );
};

export default Hero;

import Hero from "../components/hero";
import Layout from "../layouts/article";
import NavigationButton from "../components/navigationButton";

const Home = () => (
  <Layout title={"Home"}>
    <div className="container mx-auto items-center">
      <div className="container items-center max-w-full flex flex-col md:flex-row justify-evenly pt-0 md:pt-10">
        <div className="w-full md:w-md p-6">
          <Hero />
        </div>

        <div className="md:hidden pb-20">
          <NavigationButton path="/register" buttonText="Get started" />
        </div>
      </div>

      <div className="hidden md:flex justify-center mt-2">
        <NavigationButton path="/register" buttonText="Get started" />
      </div>
    </div>
  </Layout>
);

export default Home;

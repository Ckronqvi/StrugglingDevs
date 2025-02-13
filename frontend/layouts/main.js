import Head from "next/head";
import Navbar from "../components/navbar";
import Footer from "../components/footer";

const MainLayout = ({ children, router }) => {
  return (
    <div className="flex flex-col min-h-screen py-0">
      <Head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Struggling Devs" />
        <meta name="author" content="Nooa Kronqvist" />
        <link rel="shortcut icon" href="/favicon.ico" />
        <title>Struggling Devs</title>
      </Head>
      <Navbar path={router.asPath} />
      <main className="container mx-auto max-w-7xl pt-10 flex-1 mt-14">
        {children}
      </main>
      <Footer />
    </div>
  );
};

export default MainLayout;

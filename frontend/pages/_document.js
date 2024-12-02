import NextDocument, { Html, Head, Main, NextScript } from "next/document";

export default class Document extends NextDocument {
  render() {
    return (
      <Html lang="en">
        <Head>
          <link
            href="https://fonts.googleapis.com/css2?family=M+PLUS+1:wght@400;700&display=swap"
            rel="stylesheet"
          />
          <link
            href="https://fonts.googleapis.com/css2?family=M+PLUS+1+Code:wght@100..700&display=swap"
            rel="stylesheet"
          />
        </Head>
        <body className="bg-black text-white overflow-x-hidden selection:bg-white selection:text-black">
          <Main />
          <NextScript />
        </body>
      </Html>
    );
  }
}

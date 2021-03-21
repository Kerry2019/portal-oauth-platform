import Mock from "mockjs";

Mock.mock("/client/queryClients", "post", () => {
  return Mock.mock({
    "result|1-15": [
      {
        clientId: "@word(8,12)",
        "clientSecret|6": "*",
        authorizedGrantTypes: "password,refresh_token,authorization_code",
        "authorizedGrantTypesList|1-1": [
          "password",
          "refresh_token",
          "authorization_code",
        ],
        webRedirectUri: "@url",
        accessTokenValidity: 3600,
        refreshTokenValidity: 7200,
        "autoapprove|1": [true, false],
        "autoapproveMean|1": ["是", "否"],
        clientDescription: "",
      },
    ],
  });
});

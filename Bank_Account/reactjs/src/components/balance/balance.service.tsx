export interface getBalanceResponse {
  message_code: number;
  message: string;
  balance: number;
}

export default async function getBalance() {
  const response = await fetch(`http://localhost:8080/api/v1/balance`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  });
  return await response.json().then((data: getBalanceResponse) => data);
}

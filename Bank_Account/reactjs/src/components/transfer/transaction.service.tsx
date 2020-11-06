export interface postTransactionResponse {
  message_code?: number;
  message?: string;
}

export default async function postTransaction(
  Destination: string,
  Transaction_Value: number
) {
  const response = await fetch(`http://localhost:8080/api/v1/transaction`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
    body: JSON.stringify({
      Destination_Type: 1,
      Destination,
      Transaction_Value,
    }),
  });
  return await response
    .json()
    .then((data: postTransactionResponse) => data);
}

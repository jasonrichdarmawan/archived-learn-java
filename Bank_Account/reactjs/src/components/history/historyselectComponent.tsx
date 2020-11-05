import React from "react";
import getTransactions, { getTransactionsRequestBody, TransactionModel } from "./historyService";
import styles from "./history.module.css";

interface HistorySelectInterface {
  setTransactions: React.Dispatch<React.SetStateAction<TransactionModel[]>>;
  setIsTransactionsShown: React.Dispatch<React.SetStateAction<boolean>>;
}

export default function HistorySelect({
  setTransactions,
  setIsTransactionsShown,
}: HistorySelectInterface) {
  const [isHistoryDisabled, setIsHistoryDisabled] = React.useState(true);

  const Account_Number = sessionStorage.getItem("Account_Number");
  const ISO_4217 = sessionStorage.getItem("ISO_4217");

  const lang = {
    ID: {
      Intro: {
        Period: "SILAKAN PILIH PERIODE TRANSAKSI YANG AKAN DILIHAT",
        TransactionType: "SILAKAN PILIH JENIS TRANSAKSI YANG AKAN DILIHAT",
      },
      TransactionType: {
        All: "Semua Transaksi",
      },
      Note: "Catatan : Histori transaksi maksimal 31 hari yang lalu.",
    },
  };

  function handleGetTransactions({ Start, End }: getTransactionsRequestBody) {
    getTransactions({ Start, End }).then((data) => {
      data.transactions.forEach((transaction, index) => {
        if (index === 0) {
          data.transactions[index].Ending_Balance = data.Opening_Balance;
        }
        if (Account_Number === data.transactions[index].Source) {
          data.transactions[index].Ending_Balance =
            data.transactions[Math.max(0, index - 1)].Ending_Balance -
            transaction.transaction_Value;
        } else if (Account_Number === data.transactions[index].Destination) {
          data.transactions[index].Ending_Balance =
            data.transactions[Math.max(0, index - 1)].Ending_Balance +
            transaction.transaction_Value;
        }
      });
      setTransactions(data.transactions);
      setIsTransactionsShown(true);
    });
  }

  return (
    <>
      <p>{ISO_4217 === "360" && lang.ID.Intro.Period}</p>
      <div>
        <input
          type="radio"
          id="today"
          name="inputhistory"
          value="today"
          checked={isHistoryDisabled}
          onChange={() => setIsHistoryDisabled(!isHistoryDisabled)}
        />
        <label htmlFor="today">Hari Ini</label>
      </div>
      <div className={styles.flex}>
        <div className={styles.f1}>
          <input
            type="radio"
            id="history"
            name="inputhistory"
            value="history"
            checked={!isHistoryDisabled}
            onChange={() => setIsHistoryDisabled(!isHistoryDisabled)}
          />
          <label htmlFor="history">Histori</label>
        </div>
        <div className={styles.f1}>
          <label>Dari Tanggal</label>
        </div>
        <div className={styles.f1}>
          <label>Sampai Tanggal</label>
        </div>
      </div>
      <div className={styles.flex}>
        <div className={styles.f1} />
        <input type="date" className={styles.f1} disabled={isHistoryDisabled} />
        <input type="date" className={styles.f1} disabled={isHistoryDisabled} />
      </div>
      <p>{ISO_4217 === "360" && lang.ID.Intro.TransactionType}</p>
      <div>
        <input
          type="radio"
          id="all"
          name="inputTransactionType"
          checked
          readOnly
        />
        <label htmlFor="all">
          {ISO_4217 === "360" && lang.ID.TransactionType.All}
        </label>
      </div>
      <p>{ISO_4217 === "360" && lang.ID.Note}</p>
      <button
        className={styles.mright}
        onClick={() =>
          handleGetTransactions({ Start: "2020-11-05", End: "2020-11-06" })
        }
      >
        Kirim
      </button>
    </>
  );
}

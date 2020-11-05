import React from "react";
import styles from "./history.module.css";

interface HistorySelectInterface {
  ISO_4217: string | null;
  lang: any;
  isHistoryDisabled: boolean;
  setIsHistoryDisabled: React.Dispatch<React.SetStateAction<boolean>>;
  getTransactions: () => void;
}

function HistorySelect({
  ISO_4217,
  lang,
  isHistoryDisabled,
  setIsHistoryDisabled,
  getTransactions,
}: HistorySelectInterface) {
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
      <button className={styles.mright} onClick={() => getTransactions()}>
        Kirim
      </button>
    </>
  );
}

interface TransactionModel {
  Date: string;
  Source: string;
  Destination: string;
  Destination_Type: number;
  Transaction_Value: number;
  Ending_Balance: number;
}

function Transactions({ transactions }: { transactions: TransactionModel[] }) {
  const Account_Number = sessionStorage.getItem("Account_Number");
  const ISO_4217 = sessionStorage.getItem("ISO_4217");
  const lang = {
    ID: {
      Date: "Tgl.",
      Transaction_Value: "Mutasi",
      Description: "Keterangan",
      Balance: "Saldo",
    },
  };
  return (
    <>
      <table className={styles.table}>
        <thead>
          <tr>
            <th>{ISO_4217 === "360" && lang.ID.Date}</th>
            <th>{ISO_4217 === "360" && lang.ID.Description}</th>
            <th>{ISO_4217 === "360" && lang.ID.Transaction_Value}</th>
            <th>{ISO_4217 === "360" && lang.ID.Balance}</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction, index) => {
            return (
              <tr key={index}>
                <td>{transaction.Date}</td>
                <td>
                  {Account_Number === transaction.Source
                    ? `TRSF DB ${transaction.Destination}`
                    : `TRSF CR ${transaction.Source}`}
                </td>
                <td>{transaction.Transaction_Value}</td>
                <td>{transaction.Ending_Balance}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </>
  );
}

export default function History() {
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

  function getTransactions() {
    let data_Opening_Balance = 2000;
    let data_transactions: TransactionModel[] = [
      {
        Date: "2020-11-05",
        Source: "98975915454758919",
        Destination: "2",
        Destination_Type: 1,
        Transaction_Value: 2000,
        Ending_Balance: 0,
      },
      {
        Date: "2020-11-06",
        Source: "2",
        Destination: "98975915454758919",
        Destination_Type: 1,
        Transaction_Value: 2000,
        Ending_Balance: 0,
      },
    ];
    data_transactions.forEach((transaction, index) => {
      if (index === 0) {
        data_transactions[index].Ending_Balance = data_Opening_Balance;
      }
      if (Account_Number === data_transactions[index].Source) {
        data_transactions[index].Ending_Balance =
          data_transactions[Math.max(0, index - 1)].Ending_Balance -
          transaction.Transaction_Value;
      } else if (Account_Number === data_transactions[index].Destination) {
        data_transactions[index].Ending_Balance =
          data_transactions[Math.max(0, index - 1)].Ending_Balance +
          transaction.Transaction_Value;
      }
    });
    setTransactions(data_transactions);
  }

  const [transactions, setTransactions] = React.useState<TransactionModel[]>(
    []
  );

  return (
    <>
      {transactions.length === 0 ? (
        <HistorySelect
          ISO_4217={ISO_4217}
          lang={lang}
          isHistoryDisabled={isHistoryDisabled}
          setIsHistoryDisabled={setIsHistoryDisabled}
          getTransactions={getTransactions}
        />
      ) : (
        <Transactions transactions={transactions} />
      )}
    </>
  );
}

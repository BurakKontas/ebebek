package Models;

public class Employee {
    //şimdiki yılı 2021 kabul ediyoruz
    private final int _yearNow = 2021;
    private final String _name;
    private double _salary;
    private final int _workHours;
    private final int _hireYear;

    public Employee(String name, double salary, int workHours, int hireYear) {
        this._name = name;
        this._salary = salary;
        this._workHours = workHours;
        this._hireYear = hireYear;
    }

    //maaş 1000 den fazla ise %3 vergiyi döndürecek
    public double tax() {
        if(this._salary < 1000) return 0;
        else return this._salary*3/100;
    }

    //eğer 40 saatten fazla çalıştıysa 40'ın üstü her saate 30 saat bonus verecek
    public int bonus() {
        if(this._workHours < 40) return 0;
        return (_workHours-40)*30;
    }

    //çalıştığı yıla göre çalışana zam yapıyor
    public double raiseSalary() {
        int calistigiYilSayisi = this._yearNow - this._hireYear;
        double maas = this._salary+this.bonus()-this.tax();
        double artis;
        //10 yıldan az çalışıyorsa %5 zam
        if(calistigiYilSayisi < 10) {
            artis = maas*5/100;
        }
        //9 yıldan fazla ve 20 yıldan az ise %10 zam
        //yukarıdaki seçenek eğer 9 yıldan aşağıda ise
        //bu if e getirmeyeceği için bu if 9 < x < 20 şeklinde işlem görüyor
        else if(calistigiYilSayisi < 20) {
            artis = maas*10/100;
        }
        //19 yıldan fazla ise %15 zam
        //geri kalan senaryolar 19 yıldan fazla ise olduğu için if kullanmaya atmaya gerek yok
        else {
            artis = maas*15/100;
        }

        // eğer bu değeri okumak için raiseSalary i çalıştırırsam 2 kez class print ettirildiğinde 2 kez
        // zam yapacak o yüzden açmak konusunda çok emin değilim kapalı tutuyorum
        //this.salary += artis
        return artis;
    }

    @Override
    public String toString() {
      return ("""
              İsmi: %s
              Maaşı: %f
              Haftalık Çalışma Saati: %d
              Başlangıç Yılı: %d
              Vergi: %f
              Maaş Artışı: %f
              Vergi ve Bonuslarla Maaş: %f
              Toplam Maaş: %f
              """)
              .formatted(
                      this._name,
                      this._salary,
                      this._workHours,
                      this._hireYear,
                      this.tax(),
                      this.raiseSalary(),
                      this._salary-this.tax(),  //kendi maaşı dediği için zamsız halini kabul ettim
                      this._salary+this.bonus() //kendi maaşı dediği için zamsız halini kabul ettim
              );
    }
}

package com.hackerrank;

public class StringUtils {

	public static void main(String[] args) {
		StringUtils su = new StringUtils();
		System.out.println("Expected : false . Actual : " + su.isNumber("00.5"));// pass F
		System.out.println("Expected : false . Actual : " + su.isNumber("007")); // pass F
		System.out.println("Expected : true . Actual : " + su.isNumber("0.5")); // pass T
		System.out.println("Expected : true . Actual : " + su.isNumber(".05")); // fail T
		System.out.println("Expected : false . Actual : " + su.isNumber("00 5")); // pass F
		System.out.println("Expected : false . Actual : " + su.isNumber("5ER")); // pass F
		System.out.println("Expected : true . Actual : " + su.isNumber("9.")); // pass T
		System.out.println("Expected : true . Actual : " + su.isNumber("1.000"));// pass T
		System.out.println("Expected : false . Actual : " + su.isNumber("aq6")); // pass
		System.out.println("Expected : false . Actual : " + su.isNumber("9.3e")); // pass
		System.out.println("Expected : false . Actual : " + su.isNumber("0.5.5")); // pass F
		System.out.println("Expected : true . Actual : " + su.isNumber("0.0002")); // fail F

		System.out.println(su.isAnagramPalindrome("geeksogeeks"));
		System.out.println(su.isAnagramPalindrome("geeksforgeeks"));
		// su.starcase(8);
		// su.reducedString("aaabccc");
		// su.reducedString("aaabcc");
		// su.reducedString("aaabbcc");
		// su.reducedString("aabbcc");
		// su.camelCase("theMainProblemIsMoney");
		// su.camelCase("the");
		// su.camelCase("");
		// su.marsExploration("SOSSPSSQSSOR");
		// su.marsExploration("SOSSOT");
		// String s1 =
		// "ezfnjymgqtjnmstbadgdsrxvntnacwljnkgchtjeaoivfcindgxipmrjuqmmcpntpotplodjhijxqpogjmzipygacfdjgmewechuebxvcbnakszzcxkozxwavzgmesrvysonomhvufezislfntgncspthcpneyminpbjildobozfirvcgdratdpmmpkujcywvtzkdytzyfejbytsobvudvutfueveevgrqnxjiwpkrvllsjxmqhotlnpgjxkjnobxfqodlyiqsisdeuwqmntxouzdtisgutdafostmwticvncjwldpknuodmfksusaqpsoosgpiveyxipfklmhypdxpdncpgaswpycoxsuxasqduojpblctcyvyxldcgzevedvxiwinfppkjbtifuuapickknwxxjmjmtxlpfalxdgepmekaxijuphqfafrnezyldokwcnzenhpibktlfuxjfmeqajmvopbhuslnnnlmkmoteceiwbytjhhxqnkuazevswrkaofggfrnapciuoexqogscugzspwuvzkyrdfkhixcaqctfwadewpqksxxvqiigvjjpagvqikuojlwhfyztu";
		String s2 = "beabeefeab";
		String s3 = "a";
		// su.twoCharacters(s1);
		// su.twoCharacters(s2);
		// su.twoCharacters(s3);
		// su.challengingPalindromes("bac", "bac");
		// su.challengingPalindromes("abc", "def");
		// su.challengingPalindromes("jdfh", "fds");
		// su.challengingPalindromes(
		// "piiylotxanmkcljvyycmrtscndzivghxaigwxskrksqjokvncectsfxpgyorkufsaaciqgncvxtghwtpbnfskrmpzcymxugwjdixjrijytxlcasqpdljzlnxvpsbjqekrunuehmhngyumgjhboeobqnlifeskivabbaeputxuurwpowhfusacejxomosqbomleugdhzlvtzvjkzpfzyczivpioarkaxawrpxyzosglxxlpwnwnlzfjdldrffudmywwpjtlkxmoirwvolekxpmyhdyjaeqwknfpapuegircdowkuseybnbrcwscmlugzfkovxysypzekeedcemnkihrylizmakzwcdhqdxfkrvsqfhcliubnuwcjkdgpoynppagpzhmmovfuradzempdwjfvvtgmsalswwbvjahwerctoezqyyzosxhijgkzkspppvvljvedhurqyxrlhtezgphnbpuhgkmrclivkhnztoxsejnoepwpajuqrwzxnowqxqpjbxzsybtyafrqtleckzuxdlwouwijcghhgnsowijqphemojmzaieorvnbnswiuuyytmutivrdupcguvsgngmqwwskxgldlvdrmmaqhczctldsryaygnndfgjgnualnzrulirvgrhhpsozmstvdjcsmaachwigjscnapsumxpzlllvjjfoamemxuutilacvkeccfezywbftnbwyjtybgrjtqckytlhdzzuwqkcgesmfucyqpjlqxsjrjsnfuwbdcdqsajdimlrcvtjcxoettgxxjjbcnwunuuyeruotkwxkxctoveuneccenuevotcxkxwktoureyuunuwncbjjxxgtteoxcjtvcrlmidjasqdcdbwufnsjrjsxqljpqycufmsegcityhewgmvcakcxunuxogdvurumqogwljlfhysyjvvefpkbyibzeqfjhzymhdkdzueqdhnyvqtunonrxjnfbrukwlfitfmovpqn",
		// "rohznpyfcozugjybluvniqukmswcwsriwfqnhotbayaeqvyphrkwutqicjowqmqneitncvuazznjojvltijkfxgmuuxqaypftsrhsifdbppevhqedzpwomzrsdejwfsuuxekqwuzzdhltykcqtjrgbytjywbntfbwyzefccekvcalituuxmemaofjjvlllzpxmuspancsjgiwhcaamscjdvtsmzosphhrgvrilurznlaungjgfdnngyayrsdltczchqammrdvldlgxkswwqmgngsvugcpudrvitumtyyuuiwsnbnvroeiazmjomehpqjiwosnghhgcjiwuowldxuzkceltqrfaytbyszxbjpqxqwonxzwrqujapwpeonjesxotznhkvilcrmkghupbnhpgzethlrxyqruhdevjlvvpppskzkgjihxsozyyqzeotcrewhajvbwwslasmgtvvfjwdpmezdarufvommhzpgappnyopgdkjcwunbuilchfqsvrkfxdqhdcwzkamzilyrhiknmecdeekezpysyxvokfzgulmcswcrbnbyesukwodcrigeupapfnkwqeajydhympxkelovwriomxkltjpwwymduffrdldjfzlnwnwplxxlgsozyxprwaxakraoipvizcyzfpzkjvztvlzhdguelmobqsomoxjecasufhwopwruuxtupeabbaviksefilnqboeobhjgmuygnhmheunurkeqjbspvxnlzjldpqsaclxtyjirjxahdfhfrjsxyukjfmruwpjajjcvjbkxptgqcyxdcvaajmdhkbaaxqvzigybwniufucucbztnisvhqszvktilnagluptrwdgtcdzjjxcnwesqxffbxyuqmqvydzsruhxevqymjurmjnilnrkeepdsyysvbaeksozxfnzxewjrvcboxljnxjdxgfhtgrdxohnjtiynspkwgtn");
	}

}
